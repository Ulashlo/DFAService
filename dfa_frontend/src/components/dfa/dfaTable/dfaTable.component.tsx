import { DFAViewDTO } from '@src/generated/backend';
import React, { useMemo } from 'react';
import { Col, Row, Table } from 'antd';

const { Column } = Table;

export interface DfaTableProps {
  dfas: DFAViewDTO[];
  isLoading: boolean;
}

export const DfaTable = React.memo(({ dfas, isLoading }: DfaTableProps) => {
  const dataSource = useMemo(
    () =>
      dfas
        .map((dfa) => ({ ...dfa, key: dfa.address }))
        .sort((f: DFAViewDTO, s: DFAViewDTO) => f.name.localeCompare(s.name)),
    [dfas],
  );
  return (
    <Row style={{ paddingTop: '20px' }}>
      <Col span={24}>
        <Table pagination={false} size="large" dataSource={dataSource} tableLayout="fixed" loading={isLoading}>
          <Column width="15%" title="Имя" dataIndex="name" key="name" />
          <Column width="10%" title="Символ" dataIndex="symbol" key="symbol" />
          <Column width="30%" title="Адрес" dataIndex="address" key="address" />
          <Column width="30%" title="Адрес владельца" dataIndex="owner" key="owner" />
          <Column width="15%" title="Колличество токенов" dataIndex="totalSupply" key="totalSupply" />
        </Table>
      </Col>
    </Row>
  );
});
