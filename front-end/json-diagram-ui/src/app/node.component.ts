import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JsonNodeDTO } from './app.service';

@Component({
  selector: 'app-node',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './node.component.html',
  styleUrls: ['./node.component.css']
})
export class NodeComponent {
  @Input() node!: JsonNodeDTO;
}
