import React from 'react';
import { Col, Row, Tabs } from 'antd';
import { AdminRequestFormType, useAdminRequestForm } from '@src/forms/adminRequest/hook';
import { IssuerRequestsForm } from '@src/forms/adminRequest/issuerRequest';

const { TabPane } = Tabs;

export function AdminRequestForm() {
  const { adminRequestFormType, onSelectActiveTabKey } = useAdminRequestForm();

  return (
    <Row>
      <Col span={24}>
        <Tabs activeKey={adminRequestFormType} onChange={onSelectActiveTabKey}>
          <TabPane tab="Запросы на право выпуска ЦФА" key={AdminRequestFormType.ISSUER_REQUEST}>
            <IssuerRequestsForm />
          </TabPane>
        </Tabs>
      </Col>
    </Row>
  );
}
