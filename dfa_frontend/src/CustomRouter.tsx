import React from 'react';
import { BrowserRouter, Redirect, Route, RouteProps, useHistory, Switch } from 'react-router-dom';
import { BaseLayoutForm } from './forms/baseLayout';
import { NotFound } from './forms/notFound/notFound.form';
import { LoginForm } from './forms/login';
import { useAuthInfo } from './redux/hooks/auth/useAuthInfo.hook';

export interface PathParam {
  name: string;
  optional?: boolean;
}

export interface PageInfo {
  id: string;
  uri: string;
  params: PathParam[];
  requiresAuth: boolean;
  component: React.ComponentType;
  description: string;
}

export type PageName = 'login';

export const pages: Record<PageName, Readonly<PageInfo>> = {
  login: {
    id: 'auth',
    uri: '/auth',
    params: [],
    requiresAuth: false,
    component: LoginForm,
    description: 'Авторизация',
  },
};

export interface PagePanel {
  id: string;
  uri: string;
  description: string;
}

export interface ParentPagePanel {
  id: string;
  children: PagePanel[];
  description: string;
}

export const isParentPagePanel = (o: PagePanel | ParentPagePanel): o is ParentPagePanel => 'children' in o;

export const pagePanels: ReadonlyArray<PagePanel | ParentPagePanel> = [];

const getPath = (uri: string, params: PathParam[]) =>
  `${uri}${params.map((p) => `/:${p.name}${p.optional ? '?' : ''}`).join('')}`;

export function CustomRouter() {
  return (
    <BrowserRouter>
      <BaseLayoutForm>
        <Switch>
          <Route exact path={pages.login.uri} component={LoginForm} />
          {Object.entries(pages).map(([page, pageInfo]) => {
            if (pageInfo.requiresAuth) {
              return (
                <PrivateRoute
                  key={page}
                  exact
                  path={getPath(pageInfo.uri, pageInfo.params)}
                  component={pageInfo.component}
                />
              );
            }
            return (
              <Route key={page} exact path={getPath(pageInfo.uri, pageInfo.params)} component={pageInfo.component} />
            );
          })}
          <PrivateRoute component={NotFound} />
        </Switch>
      </BaseLayoutForm>
    </BrowserRouter>
  );
}

function PrivateRoute<T extends RouteProps>(props: T) {
  const authInfo = useAuthInfo();
  const history = useHistory();
  if (!authInfo) {
    return <Redirect to={{ pathname: pages.login.uri, state: { from: history.location.pathname } }} />;
  }
  return <Route {...props} />;
}
