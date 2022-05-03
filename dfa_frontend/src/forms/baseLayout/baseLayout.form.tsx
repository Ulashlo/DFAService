import { Col, ConfigProvider, Layout, Menu, Row, Space, Spin } from 'antd';
import React, { PropsWithChildren } from 'react';
import { CaretDownOutlined } from '@ant-design/icons';
import { Link } from 'react-router-dom';
import ruRu from 'antd/lib/locale/ru_RU';
import { useAuthInfo } from '@src/redux/hooks/auth/useAuthInfo.hook';
import { isParentPagePanel, PagePanel, ParentPagePanel, usersPagePanels } from '@src/CustomRouter';
import { useSpinner } from '@src/redux/hooks/spinner';
import { useBaseLayoutForm } from '@src/forms/baseLayout/hook';

const { Header, Content } = Layout;

export function BaseLayoutForm({ children }: PropsWithChildren<{}>) {
  const authInfo = useAuthInfo();
  const spinner = useSpinner();
  const { selectedMenuItem, handleLogout } = useBaseLayoutForm();

  return (
    <ConfigProvider locale={ruRu}>
      <title>DFA</title>
      <Layout style={{ background: 'white', height: '100vh' }}>
        {authInfo && (
          <Header style={{ backgroundColor: 'white', padding: '0px 15px' }}>
            <Menu style={{ fontWeight: 'bold' }} mode="horizontal" selectedKeys={[selectedMenuItem.id]}>
              {usersPagePanels
                .filter(
                  (pagePanel) => pagePanel.roleAllowed.length === 0 || authInfo?.roles.includes(pagePanel.roleAllowed),
                )
                .map((pagePanel) => {
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
          {spinner.shown && (
            <Row style={{ height: '100%' }} justify="center" align="middle">
              <Col>
                <Spin size="large" tip={spinner.message ?? 'Пожалуйста, подождите'} />
              </Col>
            </Row>
          )}
          <Row style={spinner.shown ? { display: 'none' } : { height: '100%', padding: '15px', width: '100%' }}>
            <Col span={24}>{children}</Col>
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
