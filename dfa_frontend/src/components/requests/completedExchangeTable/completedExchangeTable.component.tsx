import React, { useMemo } from 'react';
import { Col, Row, Table } from 'antd';

const { Column } = Table;

export interface CompletedExchangeRawInfo {
  id: number;
  dfaToGetName: string;
  dfaToGetSymbol: string;
  amountToGet: number;
  buyer: string;
  dfaToGiveName: string;
  dfaToGiveSymbol: string;
  amountToGive: number;
}

export interface CompletedExchangeTableInfo {
  dfaToGetName: string;
  amountToGetStr: string;
  buyer: string;
  dfaToGiveName: string;
  amountToGiveStr: string;
  key: number;
}

export interface CompletedExchangesTableProps {
  exchanges: CompletedExchangeRawInfo[];
  isLoading: boolean;
}

export const CompletedExchangesTable = React.memo(({ exchanges, isLoading }: CompletedExchangesTableProps) => {
  const dataSource = useMemo(() => {
    const source = exchanges.map((exchange) => ({
      dfaToGetName: exchange.dfaToGetName,
      amountToGetStr: `${exchange.amountToGive} ${exchange.dfaToGiveSymbol}`,
      buyer: exchange.buyer,
      dfaToGiveName: exchange.dfaToGiveName,
      amountToGiveStr: `${exchange.amountToGive} ${exchange.dfaToGiveSymbol}`,
      key: exchange.id,
    }));
    return source.sort((f: CompletedExchangeTableInfo, s: CompletedExchangeTableInfo) => f.key - s.key);
  }, [exchanges]);

  return (
    <Row style={{ paddingTop: '20px' }}>
      <Col span={24}>
        <Table pagination={false} size="large" dataSource={dataSource} tableLayout="fixed" loading={isLoading}>
          <Column width="18%" title="ЦФА на продажу" dataIndex="dfaToGiveName" key="dfaToGiveName" />
          <Column width="18%" title="Продано" dataIndex="amountToGiveStr" key="amountToGiveStr" />
          <Column width="18%" title="ЦФА на покупку" dataIndex="dfaToGetName" key="dfaToGetName" />
          <Column width="18%" title="Куплено" dataIndex="amountToGetStr" key="amountToGetStr" />
          <Column width="28%" title="Адрес пользователя для обмена" dataIndex="buyer" key="buyer" />
        </Table>
      </Col>
    </Row>
  );
});
