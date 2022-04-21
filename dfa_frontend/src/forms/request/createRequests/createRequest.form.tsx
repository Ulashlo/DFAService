import { Col, Row } from 'antd';
import React from 'react';
import { CreateRequest } from '@src/components/requests/createRequestForm';

export function CreateRequestsForm() {
  return (
    <Row justify="center">
      <Col span={12}>
        <CreateRequest />
      </Col>
    </Row>
  );
}
