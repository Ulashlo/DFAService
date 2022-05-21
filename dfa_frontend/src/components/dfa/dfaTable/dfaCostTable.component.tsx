import React, { useMemo } from 'react';
import { Col, Row, Table } from 'antd';

const { Column } = Table;

export interface DFACost {
  name: string;
  address: string;
  symbol: string;
  cost: number;
}

export interface DFACostTable {
  name: string;
  symbol: string;
  address: string;
  cost: string;
  key: string;
}

export interface DfaCostTableProps {
  dfaCosts: DFACost[];
  isLoading: boolean;
  base: string;
}

export const DfaCostTable = React.memo(({ dfaCosts, isLoading, base }: DfaCostTableProps) => {
  const dataSource = useMemo(
    () =>
      dfaCosts
        .map((dfa) => ({ ...dfa, key: dfa.address, cost: `${dfa.cost} ${base}` }))
        .sort((f: DFACostTable, s: DFACostTable) => f.name.localeCompare(s.name)),
    [dfaCosts, base],
  );
  return (
    <Row style={{ paddingTop: '20px' }}>
      <Col span={24}>
        <Table pagination={false} size="large" dataSource={dataSource} tableLayout="fixed" loading={isLoading}>
          <Column width="35%" title="Адрес" dataIndex="address" key="address" />
          <Column width="20%" title="Имя" dataIndex="name" key="name" />
          <Column width="20%" title="Символ" dataIndex="symbol" key="symbol" />
          <Column width="25%" title="Курс" dataIndex="cost" key="cost" />
        </Table>
      </Col>
    </Row>
  );
});
