import {Component, Input} from '@angular/core';
import {MenuItemGroup} from '../../../model/menu-item-group';

@Component({
  selector: 'side-menu-item-group',
  templateUrl: './side-menu-item-group.component.html',
  styleUrls: ['../../menu.css']
})
export class SideMenuItemGroupComponent {

  @Input() menuItemGroup: MenuItemGroup;
}
