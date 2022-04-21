import React, { useMemo } from 'react';
import { Col, Row, Table } from 'antd';

const { Column } = Table;

export interface Balance {
  address: string;
  owner: string;
  name: string;
  symbol: string;
  totalSupply: number;
  balance: number;
}

export interface BalanceTableProps {
  balances: Balance[];
  isLoading: boolean;
}

export const BalancesTable = React.memo(({ balances, isLoading }: BalanceTableProps) => {
  const dataSource = useMemo(
    () =>
      balances
        .map((dfa) => ({ ...dfa, key: dfa.address }))
        .sort((f: Balance, s: Balance) => f.name.localeCompare(s.name)),
    [balances],
  );
  return (
    <Row style={{ paddingTop: '20px' }}>
      <Col span={24}>
        <Table pagination={false} size="large" dataSource={dataSource} tableLayout="fixed" loading={isLoading}>
          <Column width="15%" title="Имя" dataIndex="name" key="name" />
          <Column width="10%" title="Символ" dataIndex="symbol" key="symbol" />
          <Column width="25%" title="Адрес" dataIndex="address" key="address" />
          <Column width="25%" title="Адрес владельца" dataIndex="owner" key="owner" />
          <Column width="14%" title="Колличество токенов" dataIndex="totalSupply" key="totalSupply" />
          <Column width="11%" title="Баланс" dataIndex="balance" key="balance" />
        </Table>
      </Col>
    </Row>
  );
});
