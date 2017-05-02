import { Component, OnInit } from '@angular/core';
import {ConfigurationService} from '../configuration.service';
import {Configuration} from '../configuration';
import {ActivatedRoute, Params, Router} from '@angular/router';
import { Location } from '@angular/common';
import {Message} from '../../alert-messages/message';
import {NOT_FOUND} from 'http-status-codes';
import {MessagesService} from '../../alert-messages/alert-messages.service';

@Component({
  moduleId: module.id,
  selector: 'app-configuration-detail',
  templateUrl: './configuration-detail.component.html',
  styleUrls: ['./configuration-detail.component.css'],
  providers: [ConfigurationService]
})
export class ConfigurationDetailComponent implements OnInit {
  configuration: Configuration = new Configuration();

  constructor(private configurationService: ConfigurationService, private route: ActivatedRoute,
              private location: Location, private router: Router, private messagesService: MessagesService) { }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.configurationService.getConfiguration(+params['id']))
      .subscribe(category => this.configuration = category,
        error => {
          if ((<Message>error).responseCode != null && (<Message>error).responseCode === NOT_FOUND) {
            this.router.navigate(['/configurations']);
          }
        });
  }

  goBack() {
    this.location.back();
  }

  save() {
    this.configuration.value = this.configuration.value.trim();
    if ( this.configuration.value ) {
      this.configurationService.update(this.configuration).subscribe(
        () => {
          this.goBack();
          this.messagesService.sendMessage(new Message('Configuración actualizada correctamente', false));
        },
        () => {
          this.messagesService.appendToMessage('No se pudo actualizar la configuración.');
        }
      );
    }
  }

  cancel() {
    this.goBack();
  }

}
