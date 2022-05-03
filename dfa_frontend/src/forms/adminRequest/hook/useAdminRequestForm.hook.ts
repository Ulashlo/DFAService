import { useHistory, useParams } from 'react-router-dom';
import { useCallback } from 'react';
import { pages } from '@src/CustomRouter';

export interface UseAdminRequestForm {
  adminRequestFormType: AdminRequestFormType;
  onSelectActiveTabKey: (activeKey: string) => void;
}

// eslint-disable-next-line no-shadow
export enum AdminRequestFormType {
  ISSUER_REQUEST = 'issuerRequest',
}

interface RouteParams {
  adminRequestFormType?: string;
}

export const useAdminRequestForm = (): UseAdminRequestForm => {
  const { adminRequestFormType: raw } = useParams<RouteParams>();
  const adminRequestFormType = Object.values(AdminRequestFormType).includes(raw as AdminRequestFormType)
    ? (raw as AdminRequestFormType)
    : AdminRequestFormType.ISSUER_REQUEST;

  const history = useHistory();

  const onSelectActiveTabKey = useCallback(
    (activeKey: string) => history.push(`${pages.adminRequest.uri}/${activeKey}`),
    [history],
  );

  return {
    adminRequestFormType,
    onSelectActiveTabKey,
  };
};
