import {Component, Input} from '@angular/core';
import {MenuItemGroup} from '../../../model/menu-item-group';

@Component({
  selector: 'menu-item-group',
  templateUrl: './menu-item-group.component.html',
  styleUrls: ['../../menu.css']
})
export class MenuItemGroupComponent {

  @Input() menuItemGroup: MenuItemGroup;
}
