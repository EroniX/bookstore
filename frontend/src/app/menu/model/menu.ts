import {MenuItemBase} from './menu-item-base';

export class Menu {
  menuItemBases: Array<MenuItemBase>;

  constructor(menuItemBases: Array<MenuItemBase>) {
    this.menuItemBases = menuItemBases;
  }
}
