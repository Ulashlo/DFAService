import { Col, Row } from 'antd';
import React, { useMemo } from 'react';
import { IssuerRequestsTable } from '@src/components/adminRequests/issuerRequests';
import { useIssuerRequestsInfo } from '@src/redux/hooks/issuerRequests';
import { useAutoUpdateIssuerRequests } from '@src/hooks/useAutoUpdateIssuerRequests.hook';

export function IssuerRequestsForm() {
  useAutoUpdateIssuerRequests();
  const requests = useIssuerRequestsInfo();
  const data = useMemo(() => requests.requests, [requests]);
  const isLoading = useMemo(() => !requests.isRequestsLoad, [requests]);

  return (
    <Row>
      <Col span={24}>
        <IssuerRequestsTable requests={data} isLoading={isLoading} />
      </Col>
    </Row>
  );
}
