import React from 'react';
import { Button, Col, InputNumber, Row, Typography } from 'antd';
import { BalancesTable } from '@src/components/dfa/dfaTable';
import { useBalanceForm } from '@src/forms/balance/hook';

export function BalanceForm() {
  const { isLoading, dataSource, amount, setAmount, addBalance, isAddBalanceEnabled } = useBalanceForm();

  return (
    <>
      <Row justify="end">
        <Col>
          <Typography.Title level={4}>Пополнить баланс</Typography.Title>
        </Col>
        <Col span={2} style={{ paddingLeft: '20px' }}>
          <InputNumber value={amount} onChange={setAmount} />
        </Col>
        <Col>
          <Button onClick={addBalance} disabled={!isAddBalanceEnabled}>
            Пополнить
          </Button>
        </Col>
      </Row>
      <Row>
        <Col span={24}>
          <BalancesTable balances={dataSource} isLoading={isLoading} />
        </Col>
      </Row>
    </>
  );
}
