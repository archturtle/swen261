import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'notNullOrEmpty'
})
export class NotNullOrEmptyPipe implements PipeTransform {
  transform(value: Object | null): boolean {
    return (value !== null && Object.keys(value).length > 0);
  }
}
