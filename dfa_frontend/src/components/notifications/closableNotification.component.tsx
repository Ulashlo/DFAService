import React, { ReactNode, useCallback, useRef } from 'react';
import { Button, Col, message, Row } from 'antd';
import { CloseOutlined } from '@ant-design/icons';
import { useClickAway } from 'react-use';

export interface ClosableNotificationProps {
  messageKey: string | number;
  children: ReactNode;
}

export const ClosableNotification = React.memo(({ messageKey, children }: ClosableNotificationProps) => {
  const onCloseMessage = useCallback(() => {
    message.destroy(messageKey);
  }, [messageKey]);
  const ref = useRef<HTMLDivElement>(null);

  useClickAway(ref, onCloseMessage);
  console.log(messageKey);

  return (
    <div ref={ref}>
      <Row justify="end">
        <Col>
          <Button icon={<CloseOutlined />} ghost onClick={onCloseMessage} />
        </Col>
      </Row>
      <Row>
        <Col span={24}>{children}</Col>
      </Row>
    </div>
  );
});
