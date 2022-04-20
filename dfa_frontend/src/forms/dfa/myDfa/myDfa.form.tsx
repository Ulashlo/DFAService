import React, { useMemo } from 'react';
import { Col, Row } from 'antd';
import { useDfasInfo } from '@src/redux/hooks/data';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { DfaTable } from '@src/components/dfa/dfaTable';
import { useDfasIsLoad } from '@src/redux/hooks/data/useDfasIsLoad.hook';

export function MyDfaForm() {
  const rawDfas = useDfasInfo();
  const isLoading = !useDfasIsLoad();
  const authInfo = useAuthInfo();
  const dfas = useMemo(
    () => rawDfas.filter((dfa) => authInfo?.address && dfa.owner.toLowerCase() === authInfo.address.toLowerCase()),
    [rawDfas, authInfo],
  );
  return (
    <Row>
      <Col span={24}>
        <DfaTable dfas={dfas} isLoading={isLoading} />
      </Col>
    </Row>
  );
}
