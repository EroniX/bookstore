import {MenuItemBase} from './menu-item-base';

export class MenuItemGroup extends MenuItemBase {

  children: Array<MenuItemBase>;

  constructor(children: Array<MenuItemBase>, text: string) {
    super(text);
    this.children = children;
  }
}
