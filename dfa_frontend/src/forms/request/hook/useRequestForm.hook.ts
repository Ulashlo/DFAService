import { useHistory, useParams } from 'react-router-dom';
import { useCallback } from 'react';
import { pages } from '@src/CustomRouter';

export interface UseRequestForm {
  requestFormType: RequestFormType;
  onSelectActiveTabKey: (activeKey: string) => void;
}

// eslint-disable-next-line no-shadow
export enum RequestFormType {
  MINE_REQUEST = 'mineRequest',
  ALL_REQUEST = 'allRequest',
  CREATE_REQUEST = 'createRequest',
}

interface RouteParams {
  requestFormType?: string;
}

export const useRequestForm = (): UseRequestForm => {
  const { requestFormType: raw } = useParams<RouteParams>();
  const requestFormType = Object.values(RequestFormType).includes(raw as RequestFormType)
    ? (raw as RequestFormType)
    : RequestFormType.ALL_REQUEST;

  const history = useHistory();

  const onSelectActiveTabKey = useCallback(
    (activeKey: string) => history.push(`${pages.request.uri}/${activeKey}`),
    [history],
  );

  return {
    requestFormType,
    onSelectActiveTabKey,
  };
};
