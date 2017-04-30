import { Component, OnInit } from '@angular/core';
import {Configuration} from '../configuration';
import {ConfigurationService} from '../configuration.service';

@Component({
  moduleId: module.id,
  selector: 'app-configuration-list',
  templateUrl: './configuration-list.component.html',
  styleUrls: ['./configuration-list.component.css'],
  providers: [ConfigurationService]
})
export class ConfigurationListComponent implements OnInit {
  configurations: Configuration[];

  constructor(private configurationService: ConfigurationService) {}

  ngOnInit(): void {
    this.getConfigurations();
  }

  getConfigurations(): void {
    this.configurationService.getConfigurations().subscribe(configurations => this.configurations = configurations);
  }
}
