import { useDfasIsLoad, useDfaViews } from '@src/redux/hooks/dfas';
import React, { useMemo } from 'react';
import { Col, Row } from 'antd';
import { useCompletedExchangesInfo } from '@src/redux/hooks/completedExchanges';
import { useAutoUpdateCompletedExchanges } from '@src/hooks/useAutoUpdateCompletedExchanges.hook';
import { useAutoUpdateDfas } from '@src/hooks/useAutoUpdateDfas.hook';
import { DELAY } from '@src/utils/constraints';
import { CompletedExchangeRawInfo, CompletedExchangesTable } from '@src/components/requests/completedExchangeTable';

export function ExchangeHistoryForm() {
  const exchanges = useCompletedExchangesInfo();
  const dfaViews = useDfaViews();
  const dfaIsLoad = useDfasIsLoad();
  useAutoUpdateCompletedExchanges();
  useAutoUpdateDfas(DELAY);

  const data: CompletedExchangeRawInfo[] = useMemo(
    () =>
      exchanges.completedExchanges.map((exchange) => ({
        id: exchange.id,
        dfaToGetName: dfaViews ? dfaViews[exchange.dfaToGetAddress].name : 'ошибка загрузки',
        dfaToGetSymbol: dfaViews ? dfaViews[exchange.dfaToGetAddress].symbol : 'ошибка загрузки',
        amountToGet: exchange.amountToGet,
        buyer: exchange.buyer,
        dfaToGiveName: dfaViews ? dfaViews[exchange.dfaToGiveAddress].name : 'ошибка загрузки',
        dfaToGiveSymbol: dfaViews ? dfaViews[exchange.dfaToGiveAddress].symbol : 'ошибка загрузки',
        amountToGive: exchange.amountToGive,
      })),
    [exchanges, dfaViews],
  );
  return (
    <Row>
      <Col span={24}>
        <CompletedExchangesTable exchanges={data} isLoading={!exchanges.isCompletedExchangesLoad || !dfaIsLoad} />
      </Col>
    </Row>
  );
}
