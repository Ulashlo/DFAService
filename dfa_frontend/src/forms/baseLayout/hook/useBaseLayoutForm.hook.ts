import { useApiErrorInfo } from '@src/redux/hooks/apiError';
import { useLocation } from 'react-router-dom';
import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { useCallback, useEffect, useMemo } from 'react';
import { isParentPagePanel, PagePanel, pagePanels, pages } from '@src/CustomRouter';
import NotificationService from '@src/services/NotificationService';
import { clearApiErrorInfo } from '@src/redux/reducers/apiError';
import { clearAuthInfo } from '@src/redux/reducers/auth';
import { clearRequests } from '@src/redux/reducers/requests';
import { clearBalances } from '@src/redux/reducers/balances';

export interface UseBaseLayoutForm {
  selectedMenuItem: Omit<PagePanel, 'uri'>;
  handleLogout: () => void;
}

const isCurrentPageSelected = (page: PagePanel, pathname: string) =>
  page.uri === pathname || page.uri === `/${pathname.split('/')[1]}`;

export const useBaseLayoutForm = (): UseBaseLayoutForm => {
  const apiErrorInfo = useApiErrorInfo();
  const { pathname } = useLocation();
  const dispatch = useAppDispatch();

  const selectedMenuItem = useMemo((): Omit<PagePanel, 'uri'> => {
    const selectedPagePanel = pagePanels
      .flatMap((pagePanel) => (isParentPagePanel(pagePanel) ? pagePanel.children : [pagePanel]))
      .find((pagePanel) => isCurrentPageSelected(pagePanel, pathname));
    if (selectedPagePanel) {
      return selectedPagePanel;
    }
    const selectedPage = Object.values(pages).find((page) => isCurrentPageSelected(page, pathname));
    if (selectedPage) {
      return selectedPage;
    }
    return {
      id: 'someId',
      description: '',
    };
  }, [pathname]);

  useEffect(() => {
    if (apiErrorInfo?.shown) {
      NotificationService.reportApiError(apiErrorInfo);
      dispatch(clearApiErrorInfo());
    }
  }, [apiErrorInfo, dispatch]);

  const handleLogout = useCallback(() => {
    dispatch(clearAuthInfo());
    dispatch(clearRequests());
    dispatch(clearBalances());
  }, [dispatch]);

  return {
    selectedMenuItem,
    handleLogout,
  };
};
