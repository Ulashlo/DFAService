import React from 'react';
import { Col, Row } from 'antd';
import { CreateDfa } from '@src/components/dfa/createDfaForm';

export function CreateDfaForm() {
  return (
    <Row justify="center">
      <Col span={10}>
        <CreateDfa />
      </Col>
    </Row>
  );
}
