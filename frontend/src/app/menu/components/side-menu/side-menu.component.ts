import {Component, Input} from '@angular/core';
import {Menu} from '../../model/menu';
import {MenuItem} from '../../model/menu-item';
import {MenuItemBase} from '../../model/menu-item-base';

@Component({
  selector: 'side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['../menu.css']
})
export class SideMenuComponent {

  @Input() menu: Menu;

  isMenuItem(menuItemBase: MenuItemBase) {
    return menuItemBase instanceof MenuItem;
  }
}
