import React from 'react';
import { Col, Row } from 'antd';
import { DfaTable } from '@src/components/dfa/dfaTable';
import { useDfasInfo } from '@src/redux/hooks/dfas';
import { useDfasIsLoad } from '@src/redux/hooks/dfas/useDfasIsLoad.hook';

export function AllDfaForm() {
  const dfas = useDfasInfo();
  const isLoading = !useDfasIsLoad();
  return (
    <Row>
      <Col span={24}>
        <DfaTable dfas={dfas} isLoading={isLoading} />
      </Col>
    </Row>
  );
}
