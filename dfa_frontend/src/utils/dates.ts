import { format } from 'date-fns';

const dateFormat = 'yyyy-MM-dd';
const timeFormat = 'HH:mm:ss';
const dateTimeFormat = `${dateFormat} ${timeFormat}`;

export function formatDateTime(date: Date) {
  return format(new Date(date), dateTimeFormat);
}
