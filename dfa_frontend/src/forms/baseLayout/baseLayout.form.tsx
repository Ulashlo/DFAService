import { Col, ConfigProvider, Layout, Menu, Row, Space } from 'antd';
import React, { PropsWithChildren, useCallback, useMemo } from 'react';
import { CaretDownOutlined } from '@ant-design/icons';
import { Link, useLocation } from 'react-router-dom';
import ruRu from 'antd/lib/locale/ru_RU';
import { useAuthInfo } from '@src/redux/hooks/auth/useAuthInfo.hook';
import { useAppDispatch } from '@src/redux/hooks/useAppDispatch.hook';
import { clearAuthInfo } from '@src/redux/reducers/auth';
import { isParentPagePanel, PagePanel, pagePanels, pages, ParentPagePanel } from '../../CustomRouter';

const { Header, Content } = Layout;

const isCurrentPageSelected = (page: PagePanel, pathname: string) =>
  page.uri === pathname || page.uri === `/${pathname.split('/')[1]}`;

export function BaseLayoutForm({ children }: PropsWithChildren<{}>) {
  const authInfo = useAuthInfo();
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

  const handleLogout = useCallback(() => dispatch(clearAuthInfo()), [dispatch]);

  return (
    <ConfigProvider locale={ruRu}>
      <title>DFA</title>
      <Layout>
        {authInfo && (
          <Header>
            <Menu mode="horizontal" selectedKeys={[selectedMenuItem.id]}>
              {pagePanels.map((pagePanel) => {
                if (isParentPagePanel(pagePanel)) {
                  return renderSubMenu(pagePanel);
                }
                return renderMenuItem(pagePanel);
              })}
              <Menu.Item onClick={handleLogout} key="exitPageMenuItem">
                Выйти
              </Menu.Item>
            </Menu>
          </Header>
        )}
        <Content>
          <Row>
            <Col>{children}</Col>
          </Row>
        </Content>
      </Layout>
    </ConfigProvider>
  );
}

function renderSubMenu(parentPagePanel: ParentPagePanel) {
  return (
    <Menu.SubMenu title={renderSubMenuTitle(parentPagePanel.description)} key={parentPagePanel.id}>
      {parentPagePanel.children.map(renderMenuItem)}
    </Menu.SubMenu>
  );
}

function renderSubMenuTitle(description: string) {
  return (
    <Space size="small">
      {description}
      <CaretDownOutlined />
    </Space>
  );
}

function renderMenuItem(pagePanel: PagePanel) {
  return (
    <Menu.Item key={pagePanel.id}>
      <Link to={pagePanel.uri}>{pagePanel.description}</Link>
    </Menu.Item>
  );
}
