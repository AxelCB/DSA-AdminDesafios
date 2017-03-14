import { Component, OnInit } from '@angular/core';
import {ConfigurationService} from "../configuration.service";
import {Configuration} from "../configuration";
import {ActivatedRoute, Params} from "@angular/router";
import { Location } from '@angular/common';

@Component({
  moduleId: module.id,
  selector: 'app-configuration-detail',
  templateUrl: './configuration-detail.component.html',
  styleUrls: ['./configuration-detail.component.css'],
  providers: [ConfigurationService]
})
export class ConfigurationDetailComponent implements OnInit {
  configuration: Configuration = new Configuration();

  constructor(private configurationService: ConfigurationService, private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.configurationService.getConfiguration(+params['id']))
      .subscribe(category => this.configuration = category);
  }

  goBack() {
    this.location.back();
  }

  save() {
    this.configuration.value = this.configuration.value.trim();
    if ( this.configuration.value ) {
      this.configurationService.update(this.configuration).subscribe(() => this.goBack());
    }
  }

  cancel() {
    this.goBack();
  }

}
