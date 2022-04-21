import React, { useMemo } from 'react';
import { Col, Row } from 'antd';
import { useDfasInfo, useDfasIsLoad } from '@src/redux/hooks/dfas';
import { useBalancesInfo, useBalancesIsLoad } from '@src/redux/hooks/balances';
import { useAutoUpdateDfas } from '@src/hooks/useAutoUpdateDfas.hook';
import { useAutoUpdateBalances } from '@src/hooks/useAutoUpdateBalances.hook';
import { DELAY } from '@src/utils/constraints';
import { Balance, BalancesTable } from '@src/components/dfa/dfaTable';

export function BalanceForm() {
  useAutoUpdateDfas();
  useAutoUpdateBalances(DELAY);

  const dfas = useDfasInfo();
  const isDfasLoading = !useDfasIsLoad();
  const balances = useBalancesInfo();
  const isBalancesLoading = !useBalancesIsLoad();
  const isLoading = useMemo(() => isDfasLoading || isBalancesLoading, [isDfasLoading, isBalancesLoading]);
  const dataSource = useMemo(() => {
    const addresses = new Set(balances.map((balance) => balance.address.toLowerCase()));
    return balances
      .map((balance): Balance => {
        const dfa = dfas.find((d) => addresses.has(d.address.toLowerCase()));
        if (dfa) {
          return {
            ...dfa,
            balance: balance.balance,
          };
        }
        return {
          address: '',
          owner: '',
          name: '',
          symbol: '',
          totalSupply: 0,
          balance: 0,
        };
      })
      .filter((balance) => balance.address.length > 0)
      .sort((f: Balance, s: Balance) => f.name.localeCompare(s.name));
  }, [balances, dfas]);
  return (
    <Row>
      <Col span={24}>
        <BalancesTable balances={dataSource} isLoading={isLoading} />
      </Col>
    </Row>
  );
}
