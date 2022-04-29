import React from 'react';
import { Col, Row } from 'antd';
import { BalancesTable } from '@src/components/dfa/dfaTable';
import { useBalanceForm } from '@src/forms/balance/hook';

export function BalanceForm() {
  const { isLoading, dataSource } = useBalanceForm();
  return (
    <Row>
      <Col span={24}>
        <BalancesTable balances={dataSource} isLoading={isLoading} />
      </Col>
    </Row>
  );
}
