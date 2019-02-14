import {MenuItemVisibility} from '../menu-item.enum';
import {MenuItemBase} from './menu-item-base';

export class MenuItem extends MenuItemBase {

  routeTo: string;
  visibility: MenuItemVisibility;
  permission: string;

  constructor(routeTo: string, text: string, visibility: MenuItemVisibility, permission: string) {
    super(text);
    this.routeTo = routeTo;
    this.visibility = visibility;
    this.permission = permission;
  }
}
