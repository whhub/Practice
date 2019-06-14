import {Directive, HostBinding, HostListener, OnInit, ElementRef} from '@angular/core';
import {DeviceDetailsComponent} from '../../layout/content/device-details/device-details.component';
import {Renderer2} from '@angular/core';

@Directive({
  selector: '[appGetActive]'
})
export class GetActiveDirective implements OnInit {
@HostBinding('class.active') isActive = false;
  constructor(
    private deviceDetailsComponent: DeviceDetailsComponent,
    private render: Renderer2,
    private el: ElementRef
  ) { }
  ngOnInit() {
    this.deviceDetailsComponent.addDirective(this);
  }
  @HostListener('click') myClick() {
    console.log(this.deviceDetailsComponent.serviceList);
    this.deviceDetailsComponent.serviceList. forEach(item => {
      item.isActive = false;
    });
    this.isActive = true;
  }
}
