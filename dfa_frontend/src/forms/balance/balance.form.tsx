import React, { useMemo } from 'react';
import { Col, Row } from 'antd';
import { useDfasIsLoad } from '@src/redux/hooks/dfas';
import { useBalancesInfo, useBalancesIsLoad } from '@src/redux/hooks/balances';
import { useAutoUpdateDfas } from '@src/hooks/useAutoUpdateDfas.hook';
import { useAutoUpdateBalances } from '@src/hooks/useAutoUpdateBalances.hook';
import { DELAY } from '@src/utils/constraints';
import { Balance, BalancesTable } from '@src/components/dfa/dfaTable';
import { useDfaViews } from '@src/redux/hooks/dfas/useDfaViews.hook';

export function BalanceForm() {
  useAutoUpdateDfas();
  useAutoUpdateBalances(DELAY);

  const dfas = useDfaViews();
  const isDfasLoading = !useDfasIsLoad();
  const balances = useBalancesInfo();
  const isBalancesLoading = !useBalancesIsLoad();
  const isLoading = useMemo(() => isDfasLoading || isBalancesLoading, [isDfasLoading, isBalancesLoading]);
  const dataSource = useMemo(() => {
    return balances
      .filter((balance) => balance.address.length > 0)
      .map((balance): Balance => {
        if (dfas) {
          const dfa = dfas[balance.address];
          return {
            ...dfa,
            ...balance,
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
