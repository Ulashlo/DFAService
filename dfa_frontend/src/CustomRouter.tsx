import React from 'react';
import { BrowserRouter, Redirect, Route, RouteProps, useHistory, Switch } from 'react-router-dom';
import { AuthForm } from '@src/forms/auth';
import { useAuthInfo } from '@src/redux/hooks/auth';
import { BaseLayoutForm } from './forms/baseLayout';
import { NotFound } from './forms/notFound/notFound.form';

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

export type PageName = 'auth';

export const pages: Record<PageName, Readonly<PageInfo>> = {
  auth: {
    id: 'auth',
    uri: '/auth',
    params: [],
    requiresAuth: false,
    component: AuthForm,
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
          <Route exact path={pages.auth.uri} component={AuthForm} />
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
    return <Redirect to={{ pathname: pages.auth.uri, state: { from: history.location.pathname } }} />;
  }
  return <Route {...props} />;
}
