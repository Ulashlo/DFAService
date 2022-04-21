import React, { useCallback } from 'react';
import { Col, Row, Tabs } from 'antd';
import { useHistory, useParams } from 'react-router-dom';
import { pages } from '@src/CustomRouter';
import { AllRequestsForm } from '@src/forms/request/allRequests/allRequests.form';
import { MyRequestsForm } from '@src/forms/request/myRequests';
import { CreateRequestsForm } from '@src/forms/request/createRequests';

const { TabPane } = Tabs;

// eslint-disable-next-line no-shadow
export enum RequestFormType {
  MINE_REQUEST = 'mineRequest',
  ALL_REQUEST = 'allRequest',
  CREATE_REQUEST = 'createRequest',
}

interface RouteParams {
  requestFormType?: string;
}

export function RequestForm() {
  const { requestFormType: raw } = useParams<RouteParams>();
  const requestFormType = Object.values(RequestFormType).includes(raw as RequestFormType)
    ? (raw as RequestFormType)
    : RequestFormType.ALL_REQUEST;

  const history = useHistory();

  const onSelectActiveTabKey = useCallback(
    (activeKey: string) => history.push(`${pages.request.uri}/${activeKey}`),
    [history],
  );

  return (
    <Row>
      <Col span={24}>
        <Tabs activeKey={requestFormType} onChange={onSelectActiveTabKey}>
          <TabPane tab="Все запросы на обмен" key={RequestFormType.ALL_REQUEST}>
            <AllRequestsForm />
          </TabPane>
          <TabPane tab="Мои запросы на обмен" key={RequestFormType.MINE_REQUEST}>
            <MyRequestsForm />
          </TabPane>
          <TabPane tab="Создать запросы на обмен" key={RequestFormType.CREATE_REQUEST}>
            <CreateRequestsForm />
          </TabPane>
        </Tabs>
      </Col>
    </Row>
  );
}
