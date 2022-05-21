import React, { useCallback } from 'react';
import { Col, Row, Tabs } from 'antd';
import { useHistory, useParams } from 'react-router-dom';
import { pages } from '@src/CustomRouter';
import { AllDfaForm } from '@src/forms/dfa/allDfa';
import { MyDfaForm } from '@src/forms/dfa/myDfa';
import { CreateDfaForm } from '@src/forms/dfa/createDfa';
import { useAutoUpdateDfas } from '@src/hooks/useAutoUpdateDfas.hook';
import { ISSUER } from '@src/utils/constraints';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { DfaCostForm } from '@src/forms/dfa/dfaCosts';

const { TabPane } = Tabs;

// eslint-disable-next-line no-shadow
export enum DfaFormType {
  MINE_DFA = 'mineDfa',
  ALL_DFA = 'allDfa',
  CREATE_DFA = 'createDfa',
  DFA_COSTS = 'dfaCosts',
}

interface RouteParams {
  dfaFormType?: string;
}

export function DfaForm() {
  const { dfaFormType: raw } = useParams<RouteParams>();
  const dfaFormType = Object.values(DfaFormType).includes(raw as DfaFormType)
    ? (raw as DfaFormType)
    : DfaFormType.ALL_DFA;
  const history = useHistory();
  const authInfo = useAuthInfo();

  const onSelectActiveTabKey = useCallback(
    (activeKey: string) => history.push(`${pages.dfa.uri}/${activeKey}`),
    [history],
  );

  useAutoUpdateDfas();

  return (
    <Row>
      <Col span={24}>
        <Tabs activeKey={dfaFormType} onChange={onSelectActiveTabKey}>
          <TabPane tab="Все ЦФА" key={DfaFormType.ALL_DFA}>
            <AllDfaForm />
          </TabPane>
          <TabPane tab="Цена ЦФА" key={DfaFormType.DFA_COSTS}>
            <DfaCostForm />
          </TabPane>
          {authInfo?.roles.includes(ISSUER) && (
            <>
              <TabPane tab="Мои ЦФА" key={DfaFormType.MINE_DFA}>
                <MyDfaForm />
              </TabPane>
              <TabPane tab="Создать ЦФА" key={DfaFormType.CREATE_DFA}>
                <CreateDfaForm />
              </TabPane>
            </>
          )}
        </Tabs>
      </Col>
    </Row>
  );
}
