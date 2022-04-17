import React, { useCallback } from 'react';
import { Col, Row, Tabs } from 'antd';
import { useHistory, useParams } from 'react-router-dom';
import { pages } from '@src/CustomRouter';

const { TabPane } = Tabs;

// eslint-disable-next-line no-shadow
export enum RequestFormType {
  MINE_REQUEST = 'mineRequest',
  ALL_REQUEST = 'allRequest',
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
          <TabPane tab="Все запросы на обмен" key={RequestFormType.ALL_REQUEST} />
          <TabPane tab="Мои запросы на обмен" key={RequestFormType.MINE_REQUEST} />
        </Tabs>
      </Col>
    </Row>
  );
}
