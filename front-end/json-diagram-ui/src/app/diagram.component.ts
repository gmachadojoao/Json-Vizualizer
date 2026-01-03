import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DiagramService, JsonNodeDTO } from './app.service';
import { NodeComponent } from './node.component';

@Component({
  selector: 'app-diagram',
  standalone: true,
  imports: [CommonModule, FormsModule, NodeComponent],
  templateUrl: './diagram.component.html',
  styleUrls: ['./diagram.component.css']
})
export class DiagramComponent {
  jsonInput = '';
  diagram: JsonNodeDTO | null = null;
  error: string | null = null;
  loading = false;

  constructor(private diagramService: DiagramService) {}

  generateDiagram(): void {
    this.error = null;
    this.diagram = null;

    if (!this.jsonInput.trim()) {
      this.error = 'JSON não pode estar vazio';
      return;
    }

    // Validar se é um JSON válido antes de enviar
    try {
      JSON.parse(this.jsonInput);
    } catch (e) {
      this.error = 'JSON inválido. Verifique a sintaxe.';
      return;
    }

    this.loading = true;

    this.diagramService.parseJson(this.jsonInput).subscribe({
      next: (result) => {
        this.diagram = result;
        this.loading = false;
      },
      error: (err) => {
        this.error = err.error?.message || 'Erro ao processar JSON';
        this.loading = false;
      }
    });
  }

  clearInput(): void {
    this.jsonInput = '';
    this.diagram = null;
    this.error = null;
  }
}
