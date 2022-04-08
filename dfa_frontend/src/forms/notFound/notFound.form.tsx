import React from 'react';
import { Col, Row, Typography } from 'antd';

export function NotFound() {
  return (
    <Row justify="center" align="middle">
      <Col>
        <Typography>
          <Typography.Title>Страница не найдена!</Typography.Title>
        </Typography>
      </Col>
    </Row>
  );
}
