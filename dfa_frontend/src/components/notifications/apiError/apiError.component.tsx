import { ColumnType } from 'antd/lib/table';
import ShortUniqueId from 'short-unique-id';
import React, { useCallback } from 'react';
import { ApiError } from '@src/generated/backend';
import { Col, Row, Table } from 'antd';
import { formatDateTime } from '@src/utils/dates';

const columns: ColumnType<any>[] = [
  {
    key: 'name',
    title: 'Свойство',
    dataIndex: 'name',
  },
  {
    key: 'value',
    title: 'Значение',
    dataIndex: 'value',
  },
];

const uid = new ShortUniqueId();

export const ApiErrorComponent = React.memo(({ error }: { error: ApiError }) => {
  const getObjectDataSource = useCallback(
    (): { name: string; value: string }[] => [
      { name: 'Дата', value: formatDateTime(error.errorDateTime) },
      { name: 'Код', value: error.status.toString() },
      { name: 'Сообщение', value: error.message },
    ],
    [error],
  );

  const mapObjectDataSourceElementToTableView = useCallback((el: { name: string; value: string }) => {
    return { ...el, key: uid() };
  }, []);

  const getDataSource = useCallback(
    () => getObjectDataSource().map(mapObjectDataSourceElementToTableView),
    [getObjectDataSource, mapObjectDataSourceElementToTableView],
  );

  return (
    <Row>
      <Col>
        <Table size="small" pagination={false} columns={columns} dataSource={getDataSource()} />
      </Col>
    </Row>
  );
});
