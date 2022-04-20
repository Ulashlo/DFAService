import React from 'react';
import { Col, Row } from 'antd';
import { DfaTable } from '@src/components/dfa/dfaTable';
import { useDfasInfo } from '@src/redux/hooks/data';

export function AllDfaForm() {
  const dfas = useDfasInfo();
  return (
    <Row>
      <Col span={24}>
        <DfaTable dfas={dfas} />
      </Col>
    </Row>
  );
}
