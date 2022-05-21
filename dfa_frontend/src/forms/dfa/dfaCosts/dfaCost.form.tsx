import React, { useEffect, useMemo, useState } from 'react';
import { Col, Row, Select, Typography } from 'antd';
import { useDfasInfo, useDfaViews } from '@src/redux/hooks/dfas';
import { DFACost, DfaCostTable } from '@src/components/dfa/dfaTable';
import { useDfasIsLoad } from '@src/redux/hooks/dfas/useDfasIsLoad.hook';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';

export function DfaCostForm() {
  const dfas = useDfasInfo();
  const dfaViews = useDfaViews();
  const isDfaLoading = !useDfasIsLoad();
  const [isCostLoading, setIsCostLoading] = useState<boolean>(true);
  const [selectedDfa, setSelectedDfa] = useState<string>();
  const [dfaCosts, setDfaCosts] = useState<DFACost[]>([]);
  const { dfaControllerApi } = useHttpClient();

  useEffect(() => {
    setIsCostLoading(true);
    dfaControllerApi.getDfaCost({ dfaAddress: selectedDfa ?? '' }).then((costs) => {
      const result = costs.map((cost) => ({
        name: dfaViews ? dfaViews[cost.address].name : 'ошибка загрузки',
        symbol: dfaViews ? dfaViews[cost.address].symbol : 'ошибка загрузки',
        address: cost.address,
        cost: cost.cost,
      }));
      setDfaCosts(result);
      setIsCostLoading(false);
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [dfaControllerApi, selectedDfa]);

  const isLoading = useMemo(() => isDfaLoading || isCostLoading, [isDfaLoading, isCostLoading]);

  return (
    <>
      <Row justify="start">
        <Col>
          <Typography.Title level={4}>Выберете ЦФА:</Typography.Title>
        </Col>
        <Col span={4} style={{ paddingLeft: '20px' }}>
          <Select value={selectedDfa} onChange={setSelectedDfa} style={{ width: 200 }}>
            {dfas.map((dfa) => (
              <Select.Option value={dfa.address} key={dfa.address}>{`${dfa.name} (${dfa.symbol})`}</Select.Option>
            ))}
          </Select>
        </Col>
      </Row>
      <Row style={{ paddingTop: '20px' }}>
        {dfaViews && dfaViews[selectedDfa ?? ''] && (
          <Typography.Title level={4}>{`Курс ЦФА к ${dfaViews[selectedDfa ?? ''].name}`}</Typography.Title>
        )}
      </Row>
      <Row>
        <Col span={24}>
          <DfaCostTable
            dfaCosts={dfaCosts}
            isLoading={isLoading}
            base={dfaViews && selectedDfa ? dfaViews[selectedDfa ?? ''].symbol : ''}
          />
        </Col>
      </Row>
    </>
  );
}
