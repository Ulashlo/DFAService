import React, { useMemo } from 'react';
import { Button, Col, Row, Table } from 'antd';
import { IssuerRequestDTO } from '@src/generated/backend';
import { formatDateTime } from '@src/utils/dates';
import { useHttpClient } from '@src/hooks/useHttpClient.hook';
import NotificationService from '@src/services/NotificationService';
import { useDispatch } from 'react-redux';
import { deleteIssuerRequest } from '@src/redux/reducers/issuerRequests';

const { Column } = Table;

export interface IssuerRequestTableInfo {
  username: string;
  email: string;
  dateCreated: string;
  key: string;
}

export interface IssuerRequestsTableProps {
  requests: IssuerRequestDTO[];
  isLoading: boolean;
}

export const IssuerRequestsTable = React.memo(({ requests, isLoading }: IssuerRequestsTableProps) => {
  const dataSource = useMemo(() => {
    const source = requests.map(
      (request): IssuerRequestTableInfo => ({
        username: request.userLink.username,
        email: request.userLink.email,
        dateCreated: formatDateTime(request.dateCreated),
        key: request.id,
      }),
    );
    return source.sort((f: IssuerRequestTableInfo, s: IssuerRequestTableInfo) =>
      f.dateCreated.localeCompare(s.dateCreated),
    );
  }, [requests]);
  const { requestControllerApi } = useHttpClient();
  const dispatch = useDispatch();
  return (
    <Row style={{ paddingTop: '20px' }}>
      <Col span={24}>
        <Table pagination={false} size="large" dataSource={dataSource} tableLayout="fixed" loading={isLoading}>
          <Column width="26%" title="Имя пользователя" dataIndex="username" key="username" />
          <Column width="26%" title="Email пользователя" dataIndex="string" key="string" />
          <Column width="28%" title="Дата создания заявки" dataIndex="dateCreated" key="dateCreated" />
          <Column
            width="10%"
            title=""
            key="accept"
            render={(request: IssuerRequestTableInfo) => (
              <Button
                onClick={async () => {
                  await requestControllerApi.acceptIssuerRequest({ requestId: Number.parseInt(request.key, 10) });
                  NotificationService.reportSuccess('Запрос принят!');
                  dispatch(deleteIssuerRequest(request.key));
                }}
              >
                Принять
              </Button>
            )}
          />
          <Column
            width="10%"
            title=""
            key="cansel"
            render={(request: IssuerRequestTableInfo) => (
              <Button
                danger
                onClick={async () => {
                  await requestControllerApi.canselIssuerRequest({ requestId: Number.parseInt(request.key, 10) });
                  NotificationService.reportSuccess('Запрос отклонен!');
                  dispatch(deleteIssuerRequest(request.key));
                }}
              >
                Отклонить
              </Button>
            )}
          />
        </Table>
      </Col>
    </Row>
  );
});
