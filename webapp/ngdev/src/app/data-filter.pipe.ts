import * as _ from 'lodash'
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dataFilter'
})
export class DataFilterPipe implements PipeTransform {

  transform(data: any[], query: string): any {
    if (query) {
      return _.filter(data, row => row.name.indexOf(query) > -1);
    }
    return data;
  }

}
