import { DFAViewDto } from '@src/generated/backend';
import React, { useMemo } from 'react';
import { Col, Row, Table } from 'antd';

const { Column } = Table;

export interface DfaTableProps {
  dfas: DFAViewDto[];
}

export const DfaTable = React.memo(({ dfas }: DfaTableProps) => {
  const dataSource = useMemo(
    () =>
      dfas
        .map((dfa) => ({ ...dfa, key: dfa.address }))
        .sort((f: DFAViewDto, s: DFAViewDto) => f.name.localeCompare(s.name)),
    [dfas],
  );
  return (
    <Row style={{ paddingTop: '20px' }}>
      <Col span={24}>
        <Table pagination={false} size="large" dataSource={dataSource} tableLayout="fixed">
          <Column width="22%" title="Имя" dataIndex="name" key="name" />
          <Column width="18%" title="Символ" dataIndex="symbol" key="symbol" />
          <Column width="45%" title="Адрес" dataIndex="address" key="address" />
          <Column width="15%" title="Колличество токенов" dataIndex="totalSupply" key="totalSupply" />
        </Table>
      </Col>
    </Row>
  );
});
