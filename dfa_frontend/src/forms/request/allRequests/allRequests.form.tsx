import { Col, Row } from 'antd';
import React, { useMemo } from 'react';
import { AllRequestsTable, RequestsToGetInfo } from '@src/components/requests/requestsTable';
import { useRequestsInfo } from '@src/redux/hooks/requests';
import { useDfasIsLoad, useDfaViews } from '@src/redux/hooks/dfas';
import { useAutoUpdateRequests } from '@src/hooks/useAutoUpdateRequests.hook';
import { useAutoUpdateDfas } from '@src/hooks/useAutoUpdateDfas.hook';
import { DELAY } from '@src/utils/constraints';

export function AllRequestsForm() {
  const requests = useRequestsInfo();
  const dfaViews = useDfaViews();
  const dfaIsLoad = useDfasIsLoad();
  useAutoUpdateRequests();
  useAutoUpdateDfas(DELAY);

  const data: RequestsToGetInfo[] = useMemo(
    () =>
      requests.requests.flatMap((subRequests) => {
        const dfaToGive = subRequests.dfaToGiveAddress;
        return subRequests.requestsInfo
          .flatMap((subRequest) => {
            const dfaToGet = subRequest.dfaToGetAddress;
            return subRequest.exchangeInfos.map((request) => ({
              amountToGive: request.amountToGive,
              amountToGet: request.amountToGet,
              dfaNameToGet: dfaViews ? dfaViews[dfaToGet].name : 'ошибка загрузки',
              dfaSymbolToGet: dfaViews ? dfaViews[dfaToGet].symbol : '',
              user: request.userAddress,
            }));
          })
          .map((request) => ({
            ...request,
            dfaNameToGive: dfaViews ? dfaViews[dfaToGive].name : 'ошибка загрузки',
            dfaSymbolToGive: dfaViews ? dfaViews[dfaToGive].symbol : '',
          }));
      }),
    [requests, dfaViews],
  );
  return (
    <Row>
      <Col span={24}>
        <AllRequestsTable requests={data} isLoading={!requests.idRequestsLoad || !dfaIsLoad} />
      </Col>
    </Row>
  );
}
