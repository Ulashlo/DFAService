import React from 'react';
import { Col, Row, Tabs } from 'antd';
import { AllRequestsForm } from '@src/forms/request/allRequests/allRequests.form';
import { MyRequestsForm } from '@src/forms/request/myRequests';
import { CreateRequestsForm } from '@src/forms/request/createRequests';
import { RequestFormType, useRequestForm } from '@src/forms/request/hook';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { TRADER } from '@src/utils/constraints';

const { TabPane } = Tabs;

export function RequestForm() {
  const { requestFormType, onSelectActiveTabKey } = useRequestForm();
  const authInfo = useAuthInfo();

  return (
    <Row>
      <Col span={24}>
        <Tabs activeKey={requestFormType} onChange={onSelectActiveTabKey}>
          <TabPane tab="Все запросы на обмен" key={RequestFormType.ALL_REQUEST}>
            <AllRequestsForm />
          </TabPane>
          {authInfo?.roles.includes(TRADER) && (
            <>
              <TabPane tab="Мои запросы на обмен" key={RequestFormType.MINE_REQUEST}>
                <MyRequestsForm />
              </TabPane>
              <TabPane tab="Создать запросы на обмен" key={RequestFormType.CREATE_REQUEST}>
                <CreateRequestsForm />
              </TabPane>
            </>
          )}
        </Tabs>
      </Col>
    </Row>
  );
}
