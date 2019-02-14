import {Component, Input, OnInit} from '@angular/core';
import {Menu} from '../../model/menu';
import {MenuItemBase} from '../../model/menu-item-base';
import {MenuItem} from '../../model/menu-item';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'menu',
  templateUrl: './menu.component.html',
  styleUrls: ['../menu.css']
})
export class MenuComponent {

  @Input() menu: Menu;

  isMenuItem(menuItemBase: MenuItemBase) {
    return menuItemBase instanceof MenuItem;
  }
}
