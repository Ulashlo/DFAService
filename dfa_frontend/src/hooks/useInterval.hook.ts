import { useEffect } from 'react';
import { DELAY, INTERVAL } from '@src/utils/constraints';

export const useInterval = (callback: () => any, interval: number, delay?: number) => {
  if (interval <= 0 || (delay ?? 0) < 0) {
    throw new Error(`Wrong interval=${interval} or delay=${delay}`);
  }

  useEffect(() => {
    // eslint-disable-next-line no-undef
    let delayTimerId: NodeJS.Timeout;
    // eslint-disable-next-line no-undef
    let intervalTimerId: NodeJS.Timer;
    if (!delay) {
      callback();
      intervalTimerId = setInterval(callback, INTERVAL);
    } else {
      delayTimerId = setTimeout(() => {
        callback();
        intervalTimerId = setInterval(callback, INTERVAL);
      }, DELAY);
    }
    return () => {
      if (delayTimerId) {
        clearTimeout(delayTimerId);
      }
      if (intervalTimerId) {
        clearInterval(intervalTimerId);
      }
    };
  }, [delay, interval, callback]);
};
