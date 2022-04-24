import React, { useMemo } from 'react';
import { Col, Row } from 'antd';
import { useRequestsInfo } from '@src/redux/hooks/requests';
import { useDfasIsLoad, useDfaViews } from '@src/redux/hooks/dfas';
import { useAutoUpdateRequests } from '@src/hooks/useAutoUpdateRequests.hook';
import { useAutoUpdateDfas } from '@src/hooks/useAutoUpdateDfas.hook';
import { DELAY } from '@src/utils/constraints';
import { MyRequestsTable, MyRequestsToGetInfo } from '@src/components/requests/requestsTable';
import { useAuthInfo } from '@src/redux/hooks/auth';

export function MyRequestsForm() {
  const requests = useRequestsInfo();
  const dfaViews = useDfaViews();
  const dfaIsLoad = useDfasIsLoad();
  const authInfo = useAuthInfo();
  useAutoUpdateRequests();
  useAutoUpdateDfas(DELAY);

  const data: MyRequestsToGetInfo[] = useMemo(
    () =>
      requests.requests.flatMap((subRequests) => {
        const dfaToGive = subRequests.dfaToGiveAddress;
        return subRequests.requestsInfo
          .flatMap((subRequest) => {
            const dfaToGet = subRequest.dfaToGetAddress;
            return subRequest.exchangeInfos
              .filter((request) => request.userAddress.toLowerCase() === authInfo?.address?.toLowerCase())
              .map((request) => ({
                amountToGive: request.amountToGive,
                amountToGet: request.amountToGet,
                dfaNameToGet: dfaViews ? dfaViews[dfaToGet].name : 'ошибка загрузки',
                dfaSymbolToGet: dfaViews ? dfaViews[dfaToGet].symbol : '',
              }));
          })
          .map((request) => ({
            ...request,
            dfaNameToGive: dfaViews ? dfaViews[dfaToGive].name : 'ошибка загрузки',
            dfaSymbolToGive: dfaViews ? dfaViews[dfaToGive].symbol : '',
          }));
      }),
    [requests, dfaViews, authInfo],
  );
  return (
    <Row>
      <Col span={24}>
        <MyRequestsTable requests={data} isLoading={!requests.idRequestsLoad || !dfaIsLoad} />
      </Col>
    </Row>
  );
}
