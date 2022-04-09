import React, { ReactNode } from 'react';
import { message } from 'antd';
import { AlertOutlined, WarningOutlined } from '@ant-design/icons';
import { ApiErrorInfo } from '@src/redux/reducers/apiError';
import { ClosableNotification } from '@src/components/notifications';
import { ApiErrorComponent } from '@src/components/notifications/apiError';
import { ApiError } from '../generated/backend';

let messageKey = Number.MIN_SAFE_INTEGER;

class NotificationService {
  private warningIcon = (<WarningOutlined />);

  private errorIcon = (<AlertOutlined />);

  private static getDefaultApiError(): ApiError {
    return { message: 'Неизвестная ошибка', status: 500, errorDateTime: new Date() };
  }

  public reportApiError(error: ApiErrorInfo) {
    const apiError: ApiError = error.info ?? NotificationService.getDefaultApiError();
    const content: ReactNode = <ApiErrorComponent error={apiError} />;
    message.open({
      type: 'error',
      content: <ClosableNotification messageKey={messageKey}>{content}</ClosableNotification>,
      duration: 5,
      icon: this.errorIcon,
      key: messageKey,
    });
    messageKey += 1;
  }

  // eslint-disable-next-line class-methods-use-this
  public reportSuccess(msg: string) {
    message.success(msg);
  }
}

export default new NotificationService();
