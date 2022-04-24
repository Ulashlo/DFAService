import React, { useMemo } from 'react';
import { Col, Row, Table } from 'antd';

const { Column } = Table;

export interface RequestsToGetInfo {
  dfaNameToGive: string;
  dfaSymbolToGive: string;
  amountToGive: number;
  dfaNameToGet: string;
  dfaSymbolToGet: string;
  amountToGet: number;
  user: string;
}

export interface RequestsToGetTableInfo {
  dfaNameToGive: string;
  amountToGiveStr: string;
  dfaNameToGet: string;
  amountToGetStr: string;
  user: string;
  key: number;
}

export interface RequestsTableProps {
  requests: RequestsToGetInfo[];
  isLoading: boolean;
}

export const AllRequestsTable = React.memo(({ requests, isLoading }: RequestsTableProps) => {
  let key = 0;
  const dataSource = useMemo(() => {
    const source = requests.map((request) => ({
      dfaNameToGive: request.dfaNameToGive,
      amountToGiveStr: `${request.amountToGive} ${request.dfaSymbolToGive}`,
      dfaNameToGet: request.dfaNameToGet,
      amountToGetStr: `${request.amountToGet} ${request.dfaSymbolToGet}`,
      user: request.user,
      // eslint-disable-next-line no-plusplus
      key: key++,
    }));
    return source.sort((f: RequestsToGetTableInfo, s: RequestsToGetTableInfo) =>
      f.dfaNameToGive.localeCompare(s.dfaNameToGive),
    );
  }, [requests, key]);
  return (
    <Row style={{ paddingTop: '20px' }}>
      <Col span={24}>
        <Table pagination={false} size="large" dataSource={dataSource} tableLayout="fixed" loading={isLoading}>
          <Column width="18%" title="ЦФА на продажу" dataIndex="dfaNameToGive" key="dfaNameToGive" />
          <Column width="18%" title="Колличество ЦФА на продажу" dataIndex="amountToGiveStr" key="amountToGiveStr" />
          <Column width="18%" title="ЦФА на покупку" dataIndex="dfaNameToGet" key="dfaNameToGet" />
          <Column width="18%" title="Колличество ЦФА на покупку" dataIndex="amountToGetStr" key="amountToGetStr" />
          <Column width="28%" title="Адрес создателя заявки" dataIndex="user" key="user" />
        </Table>
      </Col>
    </Row>
  );
});
