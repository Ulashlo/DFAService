import React, { useMemo } from 'react';
import { Button, Col, Row, Table } from 'antd';
import { ExchangeInfoExchangeTypeEnum } from '@src/generated/backend';
import NotificationService from '@src/services/NotificationService';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';

const { Column } = Table;

export interface MyRequestsToGetInfo {
  dfaAddressToGive: string;
  dfaNameToGive: string;
  dfaSymbolToGive: string;
  amountToGive: number;
  dfaAddressToGet: string;
  dfaNameToGet: string;
  dfaSymbolToGet: string;
  amountToGet: number;
  type: ExchangeInfoExchangeTypeEnum;
  index: number;
}

export interface MyRequestsToGetTableInfo {
  dfaAddressToGive: string;
  dfaNameToGive: string;
  amountToGiveStr: string;
  dfaAddressToGet: string;
  dfaNameToGet: string;
  amountToGetStr: string;
  key: number;
  type: string;
  index: number;
}

export interface MyRequestsTableProps {
  requests: MyRequestsToGetInfo[];
  isLoading: boolean;
}

export const MyRequestsTable = React.memo(({ requests, isLoading }: MyRequestsTableProps) => {
  const { exchangerControllerApi } = useHttpClient();
  let key = 0;
  const dataSource = useMemo(() => {
    const source = requests.map((request) => ({
      dfaAddressToGive: request.dfaAddressToGive,
      dfaNameToGive: request.dfaNameToGive,
      amountToGiveStr: `${request.amountToGive} ${request.dfaSymbolToGive}`,
      dfaAddressToGet: request.dfaAddressToGet,
      dfaNameToGet: request.dfaNameToGet,
      amountToGetStr: `${request.amountToGet} ${request.dfaSymbolToGet}`,
      type: request.type === ExchangeInfoExchangeTypeEnum.DIVISIBLE ? 'Делимая' : 'Неделимая',
      index: request.index,
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
          <Column width="20%" title="ЦФА на продажу" dataIndex="dfaNameToGive" key="dfaNameToGive" />
          <Column width="15%" title="Колличество ЦФА на продажу" dataIndex="amountToGiveStr" key="amountToGiveStr" />
          <Column width="20%" title="ЦФА на покупку" dataIndex="dfaNameToGet" key="dfaNameToGet" />
          <Column width="15%" title="Колличество ЦФА на покупку" dataIndex="amountToGetStr" key="amountToGetStr" />
          <Column width="15%" title="Тип заявки" dataIndex="type" key="type" />
          <Column
            width="15%"
            title=""
            key="delete"
            render={(request: MyRequestsToGetTableInfo) => (
              <Button
                danger
                onClick={async () => {
                  await exchangerControllerApi.deleteExchange({
                    exchangeInfoForDeleteDTO: {
                      dfaToGetAddress: request.dfaAddressToGet,
                      dfaToGiveAddress: request.dfaAddressToGive,
                      index: request.index,
                    },
                  });
                  NotificationService.reportSuccess('Запрос на удаление принят!');
                }}
              >
                Удалить
              </Button>
            )}
          />
        </Table>
      </Col>
    </Row>
  );
});
