import {Component, Input} from '@angular/core';
import {MenuItem} from '../../../model/menu-item';

@Component({
  selector: 'menu-item',
  templateUrl: './menu-item.component.html',
  styleUrls: ['../../menu.css']
})
export class MenuItemComponent {

  @Input() menuItem: MenuItem;
}
