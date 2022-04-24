import React, { useMemo } from 'react';
import { Col, Row, Table } from 'antd';

const { Column } = Table;

export interface MyRequestsToGetInfo {
  dfaNameToGive: string;
  dfaSymbolToGive: string;
  amountToGive: number;
  dfaNameToGet: string;
  dfaSymbolToGet: string;
  amountToGet: number;
}

export interface MyRequestsToGetTableInfo {
  dfaNameToGive: string;
  amountToGiveStr: string;
  dfaNameToGet: string;
  amountToGetStr: string;
  key: number;
}

export interface MyRequestsTableProps {
  requests: MyRequestsToGetInfo[];
  isLoading: boolean;
}

export const MyRequestsTable = React.memo(({ requests, isLoading }: MyRequestsTableProps) => {
  let key = 0;
  const dataSource = useMemo(() => {
    const source = requests.map((request) => ({
      dfaNameToGive: request.dfaNameToGive,
      amountToGiveStr: `${request.amountToGive} ${request.dfaSymbolToGive}`,
      dfaNameToGet: request.dfaNameToGet,
      amountToGetStr: `${request.amountToGet} ${request.dfaSymbolToGet}`,
      // eslint-disable-next-line no-plusplus
      key: key++,
    }));
    return source.sort((f: MyRequestsToGetTableInfo, s: MyRequestsToGetTableInfo) =>
      f.dfaNameToGive.localeCompare(s.dfaNameToGive),
    );
  }, [requests, key]);
  return (
    <Row style={{ paddingTop: '20px' }}>
      <Col span={24}>
        <Table pagination={false} size="large" dataSource={dataSource} tableLayout="fixed" loading={isLoading}>
          <Column width="25%" title="ЦФА на продажу" dataIndex="dfaNameToGive" key="dfaNameToGive" />
          <Column width="25%" title="Колличество ЦФА на продажу" dataIndex="amountToGiveStr" key="amountToGiveStr" />
          <Column width="25%" title="ЦФА на покупку" dataIndex="dfaNameToGet" key="dfaNameToGet" />
          <Column width="25%" title="Колличество ЦФА на покупку" dataIndex="amountToGetStr" key="amountToGetStr" />
        </Table>
      </Col>
    </Row>
  );
});
